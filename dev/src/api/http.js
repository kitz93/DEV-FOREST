import axios from "axios";

// 배포 환경마다 API 서버 주소가 달라질 수 있어 baseURL로 한 곳에서 관리한다.
// REACT_APP_API_BASE_URL이 없으면 기존과 동일하게 http://localhost를 쓴다.
const http = axios.create({
  baseURL: process.env.REACT_APP_API_BASE_URL || "http://localhost",
});

export default http;

// 서버 에러 응답이 문자열이던 시절과 ErrorResponse({status, code, message, fieldErrors})로
// 바뀐 지금 형태를 모두 처리해서 사용자에게 보여줄 메시지 하나를 뽑아낸다.
export function getErrorMessage(
  error,
  fallback = "요청 처리 중 오류가 발생했습니다."
) {
  const data = error?.response?.data;

  if (!data) {
    return fallback;
  }

  if (typeof data === "string") {
    return data;
  }

  if (data.fieldErrors && typeof data.fieldErrors === "object") {
    const messages = Object.values(data.fieldErrors).filter(Boolean);
    if (messages.length > 0) {
      return messages.join("\n");
    }
  }

  return data.message || fallback;
}
