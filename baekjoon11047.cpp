// baekjoon11047.cpp : 이 파일에는 'main' 함수가 포함됩니다. 거기서 프로그램 실행이 시작되고 종료됩니다.
//

#include <iostream>
#include <vector>
using namespace std;

int main()
{
	cin.tie(NULL);
	ios_base::sync_with_stdio(false);

	int num, money;
	int res = 0;
	vector<int> v;
	std::cin >> num >> money;

	for (int i = 0; i < num; i++)
	{
		int a;
		std::cin >> a;
		v.push_back(a);
	}
	for (int i = num-1; i >=0; i--)
	{
		res += money / v[i];
		money = money % v[i];
	}
	std::cout << res;
}