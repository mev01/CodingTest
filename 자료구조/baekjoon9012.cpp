#include <iostream>
#include <string>
using namespace std;

int main()
{
	cin.tie(NULL);
	ios_base::sync_with_stdio(false);

	int n;
	cin >> n;
	cin.ignore();
	for (int i = 0; i < n; i++) {
		string str;
		int stack = 0;
		getline(cin, str);
		int len = str.length();

		for (int j = 0; j < len && stack >= 0; j++) {
			stack += str[j] - '(' ? -1 : 1;
		}
		cout << (stack ? "NO" : "YES") << "\n";
	}
}