#include <iostream>
#include <cmath>
#include <algorithm>
#include <string>
using namespace std;

int arr[10] = { 0, };
int len = 0;

int isClick(int i) {	//i가 리모컨으로 누를수있는 숫자인지
	len = 0;
	do
	{
		if (arr[i % 10]) {
			return 0;
		}
		i /= 10;
		len++;
	} while (i != 0);
	return 1;
}

int main()
{
	cin.tie(NULL);
	ios_base::sync_with_stdio(false);

	int N, M, ans;

	cin >> N >> M;
	for (int i = 0; i < M; i++) {
		int a;
		cin >> a;
		arr[a] = 1;
	}

	ans = abs(N - 100);
	for (int i = N; i <= 1000000; i++) {	//N보다 큰 숫자를 검사해서 누를수있는 제일 작은 수를 구함
		if (isClick(i)) {
			ans = min(abs(N - i) + len, ans);
			break;
		}
	}
	for (int i = N; i >= 0; i--) {	//N보다 작은 숫자를 검사해서 누를수있는 제일 큰 수를 구함
		if (isClick(i)) {
			ans = min(abs(N - i) + len, ans);
			break;
		}
	}
	cout << ans;
}