#include <iostream>
using namespace std;

int arr[1001] = { 0,1,2 };

int main()
{
	cin.tie(NULL);
	ios_base::sync_with_stdio(false);

	for (int i = 3; i <= 1000; i++) {
		arr[i] = (arr[i - 2] + arr[i - 1]) % 10007;
	}

	int n;
	cin >> n;
	cout << arr[n];
}