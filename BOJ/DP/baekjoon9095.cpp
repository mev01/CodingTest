#include <iostream>
using namespace std;

int arr[11];

int main()
{
	cin.tie(NULL);
	ios_base::sync_with_stdio(false);

	int n;
	cin >> n;

	arr[1] = 1;
	arr[2] = 2;
	arr[3] = 4;
	for (int i = 4; i <= 10; i++) {
		arr[i] = arr[i - 3] + arr[i - 2] + arr[i - 1];
	}

	for (int i = 0; i < n; i++) {
		int a;
		cin >> a;
		cout << arr[a] << "\n";
	}
}