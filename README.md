/*扑克牌最少出牌次数
 * 众所周知，一副扑克牌按大小分为13种牌，每一种牌各四个花色，总共52张牌
 * 规定13种牌按从大到小的顺序分别为A23456789TJQK，现在从一副牌中抽取20张，每轮选择下列规则中一项出牌：
 * 1、单牌：任出一张牌
 * 2、对子：两张大小相同的牌
 * 3、三带：三张大小相同的牌，附带至多一张牌
 * 4、四带：四张大小相同的牌，附带至多两张牌
 * 5、顺子：至少五张大小连续的牌
 * 求至少需要多少轮将输入的20张牌出完？
 * 例子：	输入：“8K67A65K27T59K346AK2”（字符串）
 * 		输出：4
 * Author： Greatpan
 * */
