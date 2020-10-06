#include <iostream>
#include <string>
#include <cmath>
using namespace std;

int main()
{
	cin.tie(NULL);
	ios_base::sync_with_stdio(false);

	string N;
	cin >> N;

	int len = N.length(), n = stoi(N), answer=0;

	for (int i = 1; i < len; i++){
		answer += i * (9 * pow(10, i - 1));
	}
	answer += len * (n - pow(10, len - 1) + 1);
	
	cout << answer;
}