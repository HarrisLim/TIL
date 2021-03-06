# Tree
  - 정보를 쉽게 검색하기 위해 저장할 때 유용한 자료구조
  - 계층 구조를 추상화한 모델
  - ex) 가계도, 회사 조직

### 트리 용어
```
                              __
  level 0                    |11| (root)
                       ------|__|------ 
                      |                |
                     __                __  
  level 1           | 7|              |15|
                    |__|              |__|
                    /  \              /  \
                   /    \            /    \
                 __       __        __       __ 
  level 2       | 5|     | 9|      |13|     |20|
                |__|     |__|      |__|     |__|
                /  \     /  \      /  \     /  
               |    |   |    |    |    |   |    
              __   __   __   __   __   __   __   
  level 3    | 3| | 6| | 8| |10| |12| |14| |18|
             |__| |__| |__| |__| |__| |__| |__|


하나의 원소를 노드라고 부른다. 그 중에 최상위 노드는 루트(root)라고 부른다
내부노드: 1개 이상의 자식을 가진 노드 (ex, 7, 5, 9, 15, 13 ,20)
외부노드(== 리프): 자식이 없는 노드(3, 6, 8, 10, 12, 14, 18 ,25)
조상과 후손이 있는데, 조상은 상위 계층의 노드. 후손은 하위 계층의 노드
서브트리: 노드와 후손으로 구성된 트리 (ex, 13, 12, 14는 서브트리. 20, 18도 서브트리이다)
노드의 깊이(depth): 노드 6의 깊이는 3개의 조상(11, 7, 5)을 갖고 있으므로 3이다
트리의 높이(height): 깊이의 최대치. 위의 트리에서는 (레벨이 3까지 있으므로)3이다.
```

  - 이진트리(binary tree)
    - 최대 2개의 자식노드를 갖는다
    - 노드의 삽입, 조회, 삭제를 효과적으로 수행가능

  - 이진 탐색 트리(binary search tree)
    - 좌측 자식 노드에는 더 작은 값, 우측 자식 노드에는 더 큰 값 (위의 트리가 이진 탐색 트리)

### 이진 탐색 트리의 내부적 동작
```
내부적으로 동작하는 것을 보면 이중 연결리스트가 생각난다.

                             ___________
        (left)  ------------|-- | 11| --|-----------  (right)
               |            |___|___|___|           |
               V                (key)               V 
          ___________                          ___________     
       --|-- |  7| --|--                    --|-- | 15| --|--
      |  |___|___|___|  |                  |  |___|___|___|  |
      V                 V                  V                 V
 ___________       ___________        ___________       ___________
|   |  5|   |     |   |  9|   |      |   | 13|   |     |   | 20|   |
|___|___|___|     |___|___|___|      |___|___|___|     |___|___|___|
null     null     null     null      null     null     null     null

노드 간에 연결은 포인터로 나타낸다 

코드를 보면 이해가 더 쉽다

this.insert = funtion(key){
	ver newNode = new Node(key);

	if(root === null){ // 트리가 비어있다면
		root = newNode; // insert한 값을 root로 설정
	}else{
		insertNode(root, newNode);
	}
};

var insertNode = function(node, newNode){
	if(newNode.key < node.key){ // 입력받은 node의 key가 기존의 node의 key보다 작으면
		if(node.left === null){ // 그 node의 왼쪽 후손이 비어있으면
			node.left = newNode; // 왼쪽에 삽입
		}else{ // 후손이 비어있지 않다면 다시 그 후손과 입력받은 key를 정확한 위치를 찾을 때까지 비교 
 			insertNode(node.left, newNode);
		}
    } else{ // 입력받은 node의 key가 기존의 node.key보다 크면
    	if(node.right === null){
    		node.right = newNode;
    	} else{
    		insertNode(node.right, newNode);
    	}
    }
}
```
  - 노드 검색
    - 최소값, 최대값 찾기
      - 최소값과 최대값은 가장 왼 쪽 자식노드와 가장 오른 쪽 자식 노드인 것을 생각하자
    - 특정값 찾기
  
  - 노드 삭제
    - 리프 노드인 경우
    - 좌/우 한 쪽에만 자식이 있는 경우
    - 두 자식을 모두 가진 경우
    - 위 3가지의 경우에 따라 다르게 메소드를 정의해야 한다는 것을 주의하자


  - 트리 순회
    - 트리의 모든 노드를 방문해서 각 노드마다 어떤 작업을 수행하는 것
    - 순회 방법
      - 중위 순회
      - 전위 순회
      - 후위 순회
    - 주의
      - 트리를 순회할 때 재귀함수를 사용하는데, 재귀함수가 끝나는 지점을 주의 !

### 중위 순회
  - 노드를 오름차순으로 방문한다
  - 위의 트리를 보고 방문하는 순으로 나열해보자
    - 3 > 5 > 6 > 7 > 8 > 9 > 10 > 11 > 12 > 13 > 14 > 15 > 18 > 20

### 전위 순회
  - 자식 노드보다 노드 자신을 먼저 방문한다
  - 구조화된 문서를 출력할 때 많이 이용하는 방법
  - 방문하는 순으로 나열해보자
    - 11 > 7 > 5 > 3 > 6 > 9 > 8 > 10 > 15 > 13 > 12 > 14 > 20 > 18

### 후위 순회
  - 노드 자신보다 자식 노드를 먼저 방문한다
  - 디렉토리와 서브 디렉토리의 파일 용량을 계산할 때 쓰는 방법
  - 방문하는 순으로 나열해보자
    - 3 > 6 > 5 > 8 > 10 > 9 > 7 > 12 > 14 > 13 > 18 > 20 > 15 > 11

### AVL트리 (Adelson-Velski and Landis' tree)
  - 노드가 삭제되고 트리가 어느정도 찬 상태에서 큰 값이나 작은 값만 입력되다보면  
    한 쪽으로 기우는데, 해결 방안으로 나온 것이 AVL트리이다  
    스스로 균형을 잡는 BST(Binary Search Tree)이다
  - add, 레드-블랙 트리(Red-Black tree) 와 힙 트리(Heap tree)도 확인해보자