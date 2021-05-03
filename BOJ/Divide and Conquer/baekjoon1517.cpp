#include <iostream>
using namespace std;

int n, arr[500001], sorted[500001];
long cnt = 0;

void merge(int s1, int e1, int s2, int e2) {
	int x = s1, y = s2, pre = s1;
	while (x <= e1 && y <= e2) {
		if (arr[x] > arr[y]) {
			cnt += y - pre;
			sorted[pre++] = arr[y++];
		}
		else {
			sorted[pre++] = arr[x++];
		}
	}
	while (x <= e1) {
		sorted[pre++] = arr[x++];
	}
	while (y <= e2) {
		cnt += y - pre;
		sorted[pre++] = arr[y++];
	}
	for (int i = s1; i <= e2; i++){
		arr[i] = sorted[i];
	}
}

void divide(int s, int e) {
	if (s == e) return;
	divide(s, (s + e) / 2);
	divide((s + e) / 2 + 1, e);
	merge(s, (s + e) / 2, (s + e) / 2 + 1, e);
}

int main()
{
	cin.tie(NULL);
	ios_base::sync_with_stdio(false);

	cin >> n;
	for (int i = 1; i <= n; i++){
		cin >> arr[i];
	}
	divide(1, n);
	cout << cnt;
}