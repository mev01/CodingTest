#include <iostream>
using namespace std;

int main()
{
	cin.tie(NULL);
	ios_base::sync_with_stdio(false);

	long long n, arr[91];
	cin >> n;
	arr[1] = arr[2] = 1;
	for (int i = 3; i <= 90; i++){
		arr[i] = arr[i - 1] + arr[i - 2];
	}
	cout<< arr[n];
}