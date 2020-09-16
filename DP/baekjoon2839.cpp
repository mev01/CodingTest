#include <iostream>
#include <algorithm>
using namespace std;

int N;
int arr[5001];

void DP(int n) {
	if (arr[n - 3] < arr[n - 5]&& arr[n - 3]!=10000)
		arr[n] = arr[n - 3] + 1;
	else if(arr[n - 5] != 10000)
		arr[n] = arr[n - 5] + 1;
}

int main()
{
	cin.tie(NULL);
	ios_base::sync_with_stdio(false);

	cin >> N;

	fill_n(arr, 5001, 10000);
	arr[3] = 1;
	arr[5] = 1;
	for (int i = 6; i <= N; i++){
		DP(i);
	}
	if (arr[N] == 10000) arr[N] = -1;
	cout << arr[N];
}