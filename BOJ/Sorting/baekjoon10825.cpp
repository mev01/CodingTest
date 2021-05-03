#include <iostream>
#include <algorithm>
#include <vector>
using namespace std;

class student {
public:
	int a, b, c;
	string name;
	student(string name, int a, int b, int c) :name(name), a(a), b(b), c(c){}
};

bool comp(student a, student b) {
	return (a.a > b.a) || (a.a == b.a && a.b < b.b) || (a.a == b.a && a.b == b.b && a.c > b.c) || (a.a == b.a && a.b == b.b && a.c == b.c && a.name < b.name);
}

int main()
{
	cin.tie(NULL);
	ios_base::sync_with_stdio(false);

	int num;
	vector<student> v;
	cin >> num;
	while (num--) {
		int a, b, c;
		string name;
		cin >> name >> a >> b >> c;
		v.push_back(student(name, a, b, c));
	}
	sort(v.begin(), v.end(), comp);
	for (int i = 0; i < v.size(); i++){
		cout << v[i].name << "\n";	
	}
}