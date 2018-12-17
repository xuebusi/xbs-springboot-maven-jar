# -*- coding: utf-8 -*-
"""
Created on Tue Mar 20 22:35:03 2018

@author: piqia
"""

import sys


# 添加需要自定以的分词， 这里是使用的jieba分词，并且这里使用的文本为人民的名义，需要为分词库添加一些分词
import jieba as jieba

jieba.add_word("侯亮平")
jieba.add_word("沙瑞金")
jieba.add_word("赵东来")
jieba.add_word("京州市")


# 定义个函数式用于分词
def jiebaclearText(text):
    # 定义一个空的列表，将去除的停用词的分词保存
    mywordList = []
    # 进行分词
    seg_list = jieba.cut(text, cut_all=False)
    # 将一个generator的内容用/连接
    listStr = '/'.join(seg_list)
    # 对默认模式分词的进行遍历，去除停用词
    for myword in listStr.split('/'):
        mywordList.append(myword)
    return '/ '.join(mywordList)


def mathTest():
    sum = 0
    for i in range(1, 11):
        sum += i
        print("这是1-10累加和的第{0}个结果{1}".format(str(i), str(sum)))


if __name__ == "__main__":
    for i in range(1, len(sys.argv)):
        # 获取系统传来的参数
        text = sys.argv[i]
        text1 = jiebaclearText(text)
        print(text1)
        mathTest()
