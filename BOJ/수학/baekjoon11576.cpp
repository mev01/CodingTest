#include <iostream>
#include <cmath>
#include <vector>
using namespace std;

int main()
{
	cin.tie(NULL);
	ios_base::sync_with_stdio(false);

	int A, B, m,ans10 = 0;
	vector<int> v;
	cin >> A >> B >> m;

	while (m--){
		int a;
		cin >> a;
		ans10 += a * int(pow(A, m));
	}
	m = 0;
	while (ans10 != 0) {
		v.push_back(ans10 % B);
		ans10 = ans10 / B;
	}
	while (!v.empty()){
		cout << v.back()<<" ";
		v.pop_back();
	}
}