# 다른 컴퓨터에서 업로드할 때

  - 검색해본 결과 git push 하기 전에 git pull을 해야한다. 내 로컬 브렌치가 길 밖으로 나갔다고 하는데, 그 때 git pull --rebase를 사용하면 된다.

  ```
    ! [rejected]        master -> master (non-fast-forward)
    error: failed to push some refs to '~~.git'
    To prevent you from losing history, non-fast-forward updates were rejected
    Merge the remote changes (e.g. 'git pull') before pushing again.  See the
    'Note about fast-forwards' section of 'git push --help' for details.

  ```