#include <iostream>
using namespace std;

int inorder[100001], postorder[100001], position[100001];

void solve(int is, int ie, int ps, int pe) {
	if (is > ie || ps > pe) return;
	int root = postorder[pe], rootidx=position[root];
	cout << root << " ";

	solve(is, rootidx - 1, ps, ps + (rootidx - 1 - is));
	solve(rootidx + 1, ie, ps + (rootidx - is), pe - 1);
}

int main()
{
	cin.tie(NULL);
	ios_base::sync_with_stdio(false);

	int num;
	cin >> num;

	for (int i = 1; i <= num; i++)
		cin >> inorder[i];
	for (int i = 1; i <= num; i++) 
		cin >> postorder[i];
	for (int i = 1; i <= num; i++)
		position[inorder[i]] = i;
	
	solve(1, num, 1, num);
}