#include <stdio.h>
#include <stdint.h>
#include <stdlib.h>
#include <time.h>
#include <pthread.h>

#define LOOP_NUM (1024 * 1024)
#define THREAD_NUM 4

static void *fun_thread(void *argv)
{
	struct timeval start, end;
	gettimeofday(&start, NULL);
	uint32_t i;
	for(i = 0; i < LOOP_NUM; i++) {
		uint8_t *a = malloc(i);
		free(a);
	}
	gettimeofday(&end, NULL);
	printf("time use %ld second\n", (end.tv_sec - start.tv_sec) * 1000000 + end.tv_usec - start.tv_usec);
	return NULL;
}

void main(void)
{
	pthread_t a[THREAD_NUM];
	uint8_t i;

	for(i = 0; i < THREAD_NUM; i++)
	  pthread_create(&a[i], NULL, fun_thread, NULL);
	for(i = 0; i < THREAD_NUM; i++)
	  pthread_join(a[i], NULL);
}
