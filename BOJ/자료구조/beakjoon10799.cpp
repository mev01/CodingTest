#include <iostream>
#include <vector>
using namespace std;

int main()
{
	cin.tie(NULL);
	ios_base::sync_with_stdio(false);

	string q;
	cin >> q;
	
	int len = q.length(), cnt = 0;;
	vector<int> v;

	for (int i = 0; i < len; i++) {
		if (q[i] == '(') v.push_back(i);
		else {
			if (v.back() == i - 1) {
				v.pop_back();
				cnt += v.size();
			}
			else {
				v.pop_back();
				cnt++;
			}
		}
	}

	cout << cnt;
}