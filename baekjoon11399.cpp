// baekjoon11399.cpp : 이 파일에는 'main' 함수가 포함됩니다. 거기서 프로그램 실행이 시작되고 종료됩니다.
//

#include <iostream>
#include <algorithm>
using namespace std;

int main()
{
	cin.tie(NULL);
	ios_base::sync_with_stdio(false);

	int num,res=0;
	int time[1000];
	std::cin >> num;
	for (int i = 0; i < num; i++)
	{
		std::cin >> time[i];
	}
	sort(time, time + num);
	for (int i = 0; i < num; i++)
	{
		if (i!=0)
		{
			time[i] += time[i - 1];
		}
		res += time[i];
	}
	std::cout << res;
}