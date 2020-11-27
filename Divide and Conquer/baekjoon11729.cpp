#include <iostream>
#include <cmath>
using namespace std;

void Hanoi(int n, int s, int e) {
	if (n - 1 > 0) Hanoi(n - 1, s, 6 - s - e);
	cout << s << " " << e << "\n";
	if (n - 1 > 0) Hanoi(n - 1, 6 - s - e, e);
}

int main()
{
	cin.tie(NULL);
	ios_base::sync_with_stdio(false);

	int n;
	cin >> n;
	cout << int(pow(2, n) - 1) << "\n";
	Hanoi(n, 1, 3);
}