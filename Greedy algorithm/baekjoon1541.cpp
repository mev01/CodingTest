#include <iostream>
using namespace std;

int main()
{
	cin.tie(NULL);
	ios_base::sync_with_stdio(false);

	int res = 0, num = 0, mode=0;
	char a=' ';
	
	while (a != '\n')
	{
		a = cin.get();
		if (a >= '0' && a <= '9') {
			if (!num)	//처음 들어온 숫자
				num = a - '0';
			else
				num = num * 10 + (a - '0');
		}
		else if (a == '-') {
			if (mode == 0) res += num;
			else res -= num;
			mode = 1;
			num = 0;
		}
		else {
			if (mode == 0) res += num;
			else res -= num;
			num = 0;
		} 
	}
	cout << res;
}