#include <iostream>
using namespace std;
int arr[31] = { 1,0 };

int main()
{
	cin.tie(NULL);
	ios_base::sync_with_stdio(false);

	int n;
	cin >> n;

	for (int i = 2; i <= n; i++){
		arr[i] = 3 * arr[i - 2];
		int j = 1;
		while (i - 2 * ++j >= 0) {
			arr[i] += 2*arr[i - 2 * j];
		}
	}
	cout << arr[n];
}