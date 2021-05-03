#include <iostream>
using namespace std;

#define SIZE 1000001
#define INIT 1e4 
int answer[SIZE];

void operation(int n) {
	if (n * 3 <= SIZE)
		if (answer[n] + 1 < answer[n * 3])
			answer[n * 3] = answer[n] + 1;
	if (n * 2 <= SIZE)
		if (answer[n] + 1 < answer[n * 2])
			answer[n * 2] = answer[n] + 1;
	if (answer[n] + 1 < answer[n + 1])
		answer[n + 1] = answer[n] + 1;
}

int main()
{
	cin.tie(NULL);
	ios_base::sync_with_stdio(false);

	int num;
	cin >> num;
	fill_n(answer, SIZE, INIT);
	answer[1] = 0;

	for (int i = 1; i < num; i++){
		operation(i);
	}

	cout << answer[num];
}