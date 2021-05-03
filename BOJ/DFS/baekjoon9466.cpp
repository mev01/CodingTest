#include <iostream>
using namespace std;
int check[100001], stu[100001], pas[100001];

int DFS(int i, int cnt) {
	int ans=0;
	if (!check[i]) {
		check[i] = cnt;
		ans=DFS(stu[i], cnt + 1);
	}
	else if (!pas[i])
		return cnt - check[i];
	pas[i] = 1;
	return ans;
}

int main()
{
	cin.tie(NULL);
	ios_base::sync_with_stdio(false);

	int t, n;
	cin >> t;
	while (t--){
		int ans = 0;
		cin >> n;
		fill_n(check, n + 1, 0);
		fill_n(stu, n + 1, 0);
		fill_n(pas, n + 1, 0);
		for (int i = 1; i <= n; i++){
			cin >> stu[i];
		}
		for (int i = 1; i <= n; i++) {
			if(!check[i])
				ans += DFS(i, 1);
		}
		cout << (n - ans) << "\n";
	}
}