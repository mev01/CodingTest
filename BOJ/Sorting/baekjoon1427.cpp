#include <iostream>
#include <cmath>
#include <algorithm>
using namespace std;

void merge(int list[], int left, int mid, int right);
void merge_sort(int list[], int left, int right);

int arr[10];
int sorted[10];


int main()
{
	cin.tie(NULL);
	ios_base::sync_with_stdio(false);
	int num;
	string n;

	cin >> n;
	num = n.length();
	for (int i = 0; i < num; i++){
		arr[i] = int(n[i])-48;
	}

	merge_sort(arr, 0, num - 1);//합병 정렬

	for (int i = 0; i < num; i++) {
		cout << arr[i];
	}
}

void merge(int list[], int left, int mid, int right) {
	int i, j, k;
	i = left;
	j = mid + 1;
	k = left;

	while (i <= mid && j <= right) {
		if (list[i] >= list[j])
			sorted[k++] = list[i++];
		else
			sorted[k++] = list[j++];
	}

	if (i > mid) {
		for (int so = j; so <= right; so++)
		{
			sorted[k++] = list[so];
		}
	}
	else {
		for (int so = i; so <= mid; so++)
		{
			sorted[k++] = list[so];
		}
	}

	for (int so = left; so <= right; so++) {
		list[so] = sorted[so];
	}
}

// 합병 정렬
void merge_sort(int list[], int left, int right) {
	int mid;

	if (left < right) {
		mid = (left + right) / 2;
		merge_sort(list, left, mid);
		merge_sort(list, mid + 1, right);
		merge(list, left, mid, right);
	}
}