import os

from server import Server

'''
Launches server

@author Group 4
@version Spring 2023
'''

def main():
    os.chdir(os.path.dirname(os.path.abspath(__file__)))
    '''
    Server.run(Server, Manager())
    '''

if __name__ == '__main__':
    main()
