'''
手写识别系统
构建识别类
Recognize
调用getResult()函数即可
'''

from os import listdir

from PIL import Image
from numpy import *


def classify(inX, dataSet, labels, k):
    dataSetSize = dataSet.shape[0]    #训练数据集的行数
    # 计算距离
    diffMat = tile(inX, (dataSetSize,1)) - dataSet
    sqDiffMat = diffMat**2
    sqDistances = sqDiffMat.sum(axis=1)
    distances = sqDistances**0.5
    # 返还距离排序的索引
    sortedDistIndicies = distances.argsort()     
    classCount={}          
    for i in range(k):
        voteIlabel = labels[sortedDistIndicies[i]]
        classCount[voteIlabel] = classCount.get(voteIlabel,0) + 1
    sortedClassCount = sorted(classCount.items(), key=operator.itemgetter(1), reverse=True)
    return sortedClassCount[0][0]

# 将图片转化为行向量
def img2vector(filename):
    returnVect = zeros((1,1024))
    fr = open(filename)
    for i in range(32):
        lineStr = fr.readline()
        for j in range(32):
            returnVect[0, 32*i+j] = int(lineStr[j])
    return returnVect

def getResult(filename='temp.txt'):
    hwLabels = []
    trainingFileList = listdir('trainingDigits') # 加载训练数据集
    m = len(trainingFileList)
    trainingMat = zeros((m,1024))
    for i in range(m):
        fileNameStr = trainingFileList[i]
        fileStr = fileNameStr.split('.')[0]
        classNumStr = int(fileStr.split('_')[0])
        hwLabels.append(classNumStr)
        trainingMat[i,:] = img2vector('trainingDigits/%s' % fileNameStr)
    # 为输入的数字分类
    with open(filename, 'r') as f:
        filePath = f.read()

    fileNameStr = changeImg2Text(filePath)
    inputVect = img2vector(fileNameStr)
    classifierResult = classify(inputVect, trainingMat, hwLabels, 3)

    with open(filename, 'w') as f:
        f.write(str(classifierResult))

# 处理初始图形
def changeImg2Text(filename):
    im = Image.open(filename)
    im2 = im.resize((32, 32), Image.ANTIALIAS)
    img = array(im2)

    m, n = img.shape[:2]
    fileNameStr = filename.split('\\')[-1].split('.')[0] + '.txt'
    fr = open(fileNameStr, 'w')

    for i in range(m):
        for j in range(n):
            R, G, B = img[i, j, :]
            if R < 40 and G < 40 and B < 40:
                fr.write('1')
            else:
                fr.write('0')
        fr.write('\n')

    fr.close()
    return fileNameStr
