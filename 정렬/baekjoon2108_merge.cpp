#include <iostream>
#include <cmath>
#include <algorithm>
using namespace std;

void init(int num);
int search_mode();
void merge(int list[], int left, int mid, int right);
void merge_sort(int list[], int left, int right);

int sum = 0;
int moden[8001] = { 0, };
int arr[500000];
int sorted[500000];


int main()
{
	cin.tie(NULL);
	ios_base::sync_with_stdio(false);
	int num, avg, med, mode, scope;
	std::cin >> num;

	init(num);
	avg = int(round(double(sum) / num));//산술평균 계산
	mode = search_mode();//최빈값 계산

	merge_sort(arr, 0, num - 1);//합병 정렬
	med = arr[(num / 2 + 1)-1];//중앙값 계산
	scope = arr[num-1] - arr[0];//범위 계산
	cout << avg << "\n" << med << "\n" << mode << "\n" << scope;
}

int search_mode() {
	int index = 0, max = moden[0], on = 0;
	for (int i = 1; i <= 8000; i++)
	{
		if (max < moden[i]) {
			max = moden[i];
			index = i;
			on = 0;
		}
		else if (max == moden[i]&&on==0) {
			index = i;
			on = 1;
		}
	}
	return index - 4000;
}

void init(int num) //벡터에 원소 넣기
{
	for (int i = 0; i < num; i++)
	{
		int mem;
		std::cin >> mem;
		sum += mem;
		moden[mem + 4000]++;
		arr[i] = mem;
	}
}

void merge(int list[], int left, int mid, int right) {
	int i, j, k;
	i = left;
	j = mid + 1;
	k = left;

	while (i <= mid && j <= right) {
		if (list[i] <= list[j])
			sorted[k++] = list[i++];
		else
			sorted[k++] = list[j++];
	}

	if (i>mid) {
		for (int so = j; so <= right; so++)
		{
			sorted[k++]= list[so];
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