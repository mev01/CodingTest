#include <iostream>
#include <algorithm>
using namespace std;

void print(int num);
void insert(int num);
void Hsort(int s, int e);
void InHeap(int num);
void OutHeap(int num);

int heap[1000001];

int main()
{
	cin.tie(NULL);
	ios_base::sync_with_stdio(false);
	int num;
	std::cin >> num;

	insert(num);
	Hsort(1, num);//heapsort
	print(num);
}

void Hsort(int s, int num) {
	for (int i = 2; i <= num; i++)//원소를 하나씩 넣으면서 최대힙 구성
		InHeap(i);

	for (int i = num; i >= 2; i--)//원소를 하나씩 빼면서 오름차순 정렬
		OutHeap(i);
}

void InHeap(int num) {
	int i = num;
	while (i != 1 && heap[i / 2] < heap[i]) {//최대힙이 이미 구성되어 있고 마지막에 원소하나를 추가하는 것이므로 추가한 원소가 올라가는 것만 생각
		swap(heap[i], heap[i / 2]);
		i /= 2;
	}
}

void OutHeap(int num) {
	swap(heap[num], heap[1]);
	int pa = 1, ch = 2;
	if (num - 1 == 1) return;

	while (ch < num)//인덱스 1에 들어간 원소만 고려해 그 원소만 계속 내려준다.
	{
		if (ch < num-1 && heap[ch] < heap[ch + 1]) ch++;//경우의 수 1. 부모의 자식이 왼쪽 자식밖에 없을 때 2. 자식의 두명이지만 오른쪽 자식이 더 클때
														//두개의 경우 하나라도 만족시키지 못하는 경우 오른쪽 자식은 고려할 필요 없다.
		if (heap[pa] > heap[ch]) break;//부모가 더 큰 경우 이미 최대힙이 만들어져 있으므로 break
		swap(heap[pa], heap[ch]);//더 큰 자식이 ch에 들어가 있으므로 swap
			
		pa = ch;
		ch *= 2;
	}
}

void print(int num)	//배열 원소 출력 
{
	for (int i = 1; i <= num; i++)
		cout << heap[i] << "\n";
}

void insert(int num)//배열 삽입
{
	for (int i = 1; i <= num; i++)
	{
		int mem;
		std::cin >> mem;
		heap[i] = mem;
	}
}