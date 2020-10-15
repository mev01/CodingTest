#include <iostream>
using namespace std;

int gcd(int a, int b) {
	while (b != 0) {
		int r = a % b;
		a = b;
		b = r;
	}
	return a;
}

int lcm(int a, int b) {
	return a * b / gcd(a, b);
}

int main()
{
	cin.tie(NULL);
	ios_base::sync_with_stdio(false);

	int n;
	cin >> n;
	for (int t = 0; t < n; t++) {
		int N, M, x, y, cnt = -1, max;
		cin >> N >> M >> x >> y;

		int G = gcd(M, N);

		if ((y - x) % G) {
			cout << "-1\n";
			continue;
		}

		max = lcm(N, M) / N;	//최소공배수를 N으로 나누어 반복문 횟수 구함

		for (int i = 0; i < max; i++) {
			if ((x + N * i) % M == y % M) {//x + N * i가 전체 횟수로 이를 통해 x에 대한 y가 맞는지 확인
				cnt = x + N * i;//맞으면 현재 횟수를 저장
				break;
			}
		}
		cout << cnt<<"\n";
	}
}