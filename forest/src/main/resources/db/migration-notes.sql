-- 적용은 하지 않고 참고용으로만 남겨둔다 (교육원 DB에 접속 권한이 없어 실행 불가).

-- 동일 유저의 중복 참석을 DB 레벨에서도 차단 (STATUS='Y'인 행만 대상)
CREATE UNIQUE INDEX UX_STUDYING_ACTIVE
  ON TB_STUDYING (CASE WHEN STATUS = 'Y' THEN REF_RNO END,
                  CASE WHEN STATUS = 'Y' THEN STUDYING_USER END);
