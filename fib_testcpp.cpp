#include<iostream>
#include<stdlib.h>
using namespace std;

void fibonacci(int num) {
	if (num <= 0) {
		cout << "Please input a positive number:" << endl;
		cin >> num;
		fibonacci(num);
	} else {
		int a = 1, b = a;
		int i = 1;
		cout << a << " ";
		for (; i < num; i++) {
			int temp;
			cout << b << " ";
			temp = b;
			b += a;
			a = temp;
		}
		cout << "\n" << "==========================" << endl;
	}	

}

int main() {
	while (1) {
		int num;
		// method
		cin >> num;
		fibonacci(num);	
	}
	
	return 0;
}
