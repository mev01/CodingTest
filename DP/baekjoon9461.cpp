#include <iostream>
using namespace std;
long long arr[101] = { 0,1,1,1 };

int main()
{
	cin.tie(NULL);
	ios_base::sync_with_stdio(false);

	int T, N;
	cin >> T;
	for (int i = 4; i <= 100; i++){
		arr[i] = arr[i - 3] + arr[i - 2];
	}
	while (T--){
		cin >> N;
		cout << arr[N] << "\n";
	}
}