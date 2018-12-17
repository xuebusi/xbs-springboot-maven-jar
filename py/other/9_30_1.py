import sys

from selenium import webdriver


def main(a):
    driver = webdriver.Chrome()  # 打开浏览器
    driver.get(a)  # 浏览器打开指定页面


if __name__ == '__main__':
    # for i in range(1, len(sys.argv)):
        # url = sys.argv[i]
        url = 'http://blog.csdn.net/thorny_v/article/details/61417386'
        main(url)
