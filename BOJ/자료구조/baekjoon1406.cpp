#include <iostream>
#include <deque>
using namespace std;

int main()
{
	cin.tie(NULL);
	ios_base::sync_with_stdio(false);

	string a;
	deque<char> left,right;

	cin >> a;
	int siz = a.size();
	for (int i = 0; i < siz; i++){	//left에 문자열 넣기
		left.push_back(a[i]);
	}
	
	int n, cur = siz;
	cin >> n;
	for (int i = 0; i < n; i++){	//명령어 수행, deque 두개를 가지고 연산
		char cmd;
		cin >> cmd;

		if (cmd == 'L' && left.size()) {
			right.push_front(left.back());
			left.pop_back();
		}
		else if (cmd == 'D' && right.size()) {
			left.push_back(right.front());
			right.pop_front();
		}
		else if (cmd == 'B' && left.size()) left.pop_back();
		else if (cmd == 'P') {
			char ch;
			cin >> ch;
			left.push_back(ch);
		}
	}
	for (int i = 0; i < left.size(); i++) {
		cout << left[i];
	}
	for (int i = 0; i < right.size(); i++) {
		cout << right[i];
	}
}