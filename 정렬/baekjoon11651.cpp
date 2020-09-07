#include <iostream>
#include <algorithm>
#include <vector>
using namespace std;

class Dot {
public:
	int x, y;
	Dot(int x, int y) : x(x), y(y) { }
};

bool Compare(Dot a, Dot b) {
	if (a.y == b.y)
		return a.x < b.x;
	else
		return a.y < b.y;
}

int main()
{
	cin.tie(NULL);
	ios_base::sync_with_stdio(false);

	int num;
	vector<Dot> v;

	cin >> num;

	for (int i = 0; i < num; i++)
	{
		int x, y;
		cin >> x >> y;
		v.push_back(Dot(x, y));
	}

	sort(v.begin(), v.end(), Compare);

	for (int i = 0; i < num; i++)
	{
		cout << v[i].x << " " << v[i].y << "\n";
	}
}