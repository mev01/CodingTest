// baekjoon1003.cpp : 이 파일에는 'main' 함수가 포함됩니다. 거기서 프로그램 실행이 시작되고 종료됩니다.
//

#include <iostream>
using namespace std;

void fibonacci(int n);
int fibo_0[41] = { 0, };
int fibo_1[41] = { 0, };

int main()
{
	cin.tie(NULL);
	ios_base::sync_with_stdio(false);

	int num;
	fibo_0[0] = 1;
	fibo_1[1] = 1;

	std::cin >> num;
	for (int i = 0; i < num; i++)
	{
		int fibo;
		std::cin >> fibo;
		fibonacci(fibo);
		std::cout << fibo_0[fibo] << " " << fibo_1[fibo] << "\n";
	}
}

void fibonacci(int n) {
	 if (n == 0) {
		return;
	}
	else if (n == 1) {
		return;
	}
	else if (fibo_0[n] != 0 || fibo_1[n] != 0)
	 {
		 return;
	 }
	if (fibo_0[n-1] != 0 || fibo_1[n-1] != 0)
	{
		 fibo_0[n] += fibo_0[n - 1];
		 fibo_1[n] += fibo_1[n - 1];
	}
	else{
		fibonacci(n - 1);
		fibo_0[n] += fibo_0[n - 1];
		fibo_1[n] += fibo_1[n - 1];
	}
	if (fibo_0[n - 2] != 0 || fibo_1[n - 2] != 0)
	 {
		 fibo_0[n] += fibo_0[n - 2];
		 fibo_1[n] += fibo_1[n - 2];
	 }
	else {
		 fibonacci(n - 2);
		 fibo_0[n] += fibo_0[n - 2];
		 fibo_1[n] += fibo_1[n - 2];
	}
}

// 프로그램 실행: <Ctrl+F5> 또는 [디버그] > [디버깅하지 않고 시작] 메뉴
// 프로그램 디버그: <F5> 키 또는 [디버그] > [디버깅 시작] 메뉴

// 시작을 위한 팁: 
//   1. [솔루션 탐색기] 창을 사용하여 파일을 추가/관리합니다.
//   2. [팀 탐색기] 창을 사용하여 소스 제어에 연결합니다.
//   3. [출력] 창을 사용하여 빌드 출력 및 기타 메시지를 확인합니다.
//   4. [오류 목록] 창을 사용하여 오류를 봅니다.
//   5. [프로젝트] > [새 항목 추가]로 이동하여 새 코드 파일을 만들거나, [프로젝트] > [기존 항목 추가]로 이동하여 기존 코드 파일을 프로젝트에 추가합니다.
//   6. 나중에 이 프로젝트를 다시 열려면 [파일] > [열기] > [프로젝트]로 이동하고 .sln 파일을 선택합니다.
