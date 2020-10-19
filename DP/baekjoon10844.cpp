#include <iostream>
using namespace std;
long arr[101][11] = { 0,0,0,0,0,0,0,0,0,0,0,0,1,1,1,1,1,1,1,1,1,0 };

int main()
{
	cin.tie(NULL);
	ios_base::sync_with_stdio(false);

	int n;
	long cnt = 0;
	cin >> n;

	for (int i = 2; i <= n; i++){
		for (int j = 0; j < 10; j++){
			arr[i][j] = (arr[i - 1][j - 1] + arr[i - 1][j + 1])% 1000000000;
		}
	}
	for (int i = 0; i < 10; i++){
		cnt += arr[n][i];
	}
	cout << cnt % 1000000000;
}