#include <iostream>
#include <vector>
using namespace std;

void print_vec(vector<int> v, int num);
vector<int> init(vector<int> v, int num);
void Qsort(int s, int e);
vector<int> v;



int main()
{
	cin.tie(NULL);
	ios_base::sync_with_stdio(false);
	int num;
	std::cin >> num;

	v = init(v, num);
	Qsort(1, num);
	print_vec(v, num);
}

void Qsort(int s, int e) { //퀵정렬
	if (s >= e) return;
	swap(v[s], v[(s+e)/2]);
	int i = s + 1, j = e;
	int pivot = s;
	while (i <= j) {
		if (v[i] > v[pivot] && v[j] < v[pivot])
			swap(v[i++], v[j--]);
		else if (v[i] > v[pivot])
			j--;
		else if (v[j] < v[pivot])
			i++;
		else {
			i++; j--;
		}	
	}
	swap(v[pivot], v[j]);
	Qsort(s, j - 1);
	Qsort(j+1, e);
}

void print_vec(vector<int> v, int num)	//벡터의 원소 출력 
{
	for (int i = 1; i <= num; i++)
	{
		std::cout << v[i] << "\n";
	}
}

vector<int> init(vector<int> v, int num) //벡터에 원소 넣기
{
	for (int i = 0; i <= num; i++)
	{
		if (i == 0)
		{
			v.push_back(2);
		}
		else
		{
			int mem;
			std::cin >> mem;
			v.push_back(mem);
		}
	}
	return v;
}