# lock-game

修改getNext方法以获取正确的sequence，目标：
* 不重号
* 不掉号（要求可以放宽一点，允许部分掉号）
* 耗时短（起码要比synchronized好吧，这个好像很难...）
