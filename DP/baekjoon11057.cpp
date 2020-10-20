#include <iostream>
using namespace std;
int arr[10] = {1,1,1,1,1,1,1,1,1,1 };

int main()
{
	cin.tie(NULL);
	ios_base::sync_with_stdio(false);

	int n, cnt = 0;
	cin >> n;
	for (int i = 2; i <= n; i++){
		for (int j = 1; j < 10; j++){
			arr[j] = (arr[j - 1] + arr[j])%10007;
		}
	}
	for (int i = 0; i <= 9; i++) {
		cnt += arr[i];
	}
	cout << cnt % 10007;
}