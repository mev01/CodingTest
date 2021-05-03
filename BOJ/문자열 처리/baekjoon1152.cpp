#include <iostream>
#include <string>
using namespace std;

int main()
{
	cin.tie(NULL);
	ios_base::sync_with_stdio(false);

	string sen;
	getline(cin, sen);
	int num;
	int cnt = 1;
	num = sen.length();
	for (int i = 0; i < num; i++)
	{
		if (i == 0)
			if (sen[i] == ' ')
				cnt--;
		if (i == num - 1) {
			if (sen[i] == ' ')
				break;
		}
		if (sen[i] == ' ')
			cnt++;
	}
	cout << cnt;
}
