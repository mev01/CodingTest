// baekjoon1931.cpp : 이 파일에는 'main' 함수가 포함됩니다. 거기서 프로그램 실행이 시작되고 종료됩니다.
//

#include <iostream>
#include <algorithm>
#include <utility>
#include <vector>
using namespace std;


int main()
{
	cin.tie(NULL);
	ios_base::sync_with_stdio(false);

	int num = 0;
	int end=0;
	int res=1;
	std::vector<pair<int, int>>meet;
	std::cin >> num;

	for (int i = 0; i < num; i++)
	{
		int x, y;
		std::cin >> x >> y;
		meet.push_back(make_pair(y,x));
	}
	sort(meet.begin(), meet.end());

	for (int i = 0; i < num; i++)
	{
		if (i==0)
		{
			end = meet[0].first;
		}
		else if (meet[i].second>=end)
		{
			end = meet[i].first;
			res++;
		}
	}
	std::cout << res;
}
