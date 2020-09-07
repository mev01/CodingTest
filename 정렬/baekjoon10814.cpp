#include <iostream>
#include <algorithm>
#include <vector>
using namespace std;

class People {
public:
	int age, num;
	string name;
	People(int age, string name, int num) : age(age), name(name), num(num) { }
};

bool Compare(People a, People b) {
	if (a.age == b.age)
		return a.num < b.num;
	else
		return a.age < b.age;
}

int main()
{
	cin.tie(NULL);
	ios_base::sync_with_stdio(false);

	int num;
	vector<People> v;

	cin >> num;

	for (int i = 0; i < num; i++)
	{
		int age;
		string name;
		cin >> age >> name;
		v.push_back(People(age, name, i + 1));
	}

	sort(v.begin(), v.end(), Compare);

	for (int i = 0; i < num; i++)
	{
		cout << v[i].age << " " << v[i].name << "\n";
	}
}