#include <iostream>
using namespace std;

int Tree[26][2];

void Preorder(int i) {
	cout << char(i + 'A');
	if (Tree[i][0] >= 0)
		Preorder(Tree[i][0]);
	if (Tree[i][1] >= 0)
		Preorder(Tree[i][1]);
}

void Inorder(int i) {
	if (Tree[i][0] >= 0)
		Inorder(Tree[i][0]);
	cout << char(i + 'A');
	if (Tree[i][1] >= 0)
		Inorder(Tree[i][1]);
}

void Postorder(int i) {
	if (Tree[i][0] >= 0)
		Postorder(Tree[i][0]);
	if (Tree[i][1] >= 0)
		Postorder(Tree[i][1]);
	cout << char(i + 'A');
}


int main()
{
	cin.tie(NULL);
	ios_base::sync_with_stdio(false);

	int n;
	cin >> n;
	while (n--){
		char a, b, c;
		cin >> a >> b >> c;
		Tree[a - 'A'][0] = b - 'A';
		Tree[a - 'A'][1] = c - 'A';
	}
	Preorder(0);
	cout << "\n";
	Inorder(0);
	cout << "\n";
	Postorder(0);
}