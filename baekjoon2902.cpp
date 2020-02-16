#include <iostream>
#include <string>
using namespace std;

int main()
{
	cin.tie(NULL);
	ios_base::sync_with_stdio(false);

	string sen;
	cin >> sen;
	int num;
	num = sen.length();
	for (int i = 0; i < num; i++)
	{
		if (sen[i] >= 'A' && sen[i] <= 'Z')
			cout << sen[i];
	}
}