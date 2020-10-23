#include <iostream>
using namespace std;
long arr[5001] = { 1 };

int main()
{
	cin.tie(NULL);
	ios_base::sync_with_stdio(false);

	string str;
	int len;
	cin >> str;
	len = str.length();

	for (int i = 1; i <= len; i++){
		if (i == 1) {
			int a = (str[i - 1] - '0');
			if (a % 10 >= 1) arr[i] += arr[i - 1];
			continue;
		}
		int a = (str[i - 2] - '0') * 10 + (str[i - 1] - '0');
		if (a >= 10 && a <= 26) arr[i] += arr[i - 2];
		if (a % 10 >= 1) arr[i] += arr[i - 1];
		arr[i] %= 1000000;
	}
	cout << arr[len];
}