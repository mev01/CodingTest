// baekjoon4307.cpp : 이 파일에는 'main' 함수가 포함됩니다. 거기서 프로그램 실행이 시작되고 종료됩니다.
//

#include <iostream>
#include <vector>
#include <math.h>
#include <stdlib.h>
using namespace std;

int main()
{
	cin.tie(NULL);
	ios_base::sync_with_stdio(false);

	int num;
	int res_fast=0, res_late=0;
	
	std::cin >> num;
	for (int i = 0; i < num; i++)
	{
		int len, n;
		vector<int> v;
		std::cin >> len >> n;
		for (int i = 0; i < n; i++)
		{
			int sta;
			std::cin >> sta;
			v.push_back(sta);
		}
		int dis_late = len/2;
		int dis_fast = 0;
		for (int i = 0; i < n; i++)
		{
			if (abs(v[i] - (len / 2)) < dis_late)
			{
				dis_late = abs(v[i] - (len / 2));
			}
			if (abs(v[i] - (len / 2)) > dis_fast)
			{
				dis_fast = abs(v[i] - (len / 2));
			}
		}
		res_late = len / 2 - dis_late;
		res_fast = len / 2 + dis_fast;
		std::cout << res_late<<" "<<res_fast<<"\n";
	}
}