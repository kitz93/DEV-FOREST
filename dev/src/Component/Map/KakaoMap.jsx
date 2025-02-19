import React, { useEffect, useState } from "react";

const { kakao } = window;

const KakaoMap = ({ isOpen, onClose, onSelectPlace }) => {
  const [searchKeyword, setSearchKeyword] = useState("");
  const [places, setPlaces] = useState([]);
  const [map, setMap] = useState(null);
  const [markers, setMarkers] = useState([]);
  const [center, setCenter] = useState({
    lat: 37.566826, // 기본 위치: 서울
    lng: 126.9786567,
  });

  // ✅ 현재 위치 가져오기
  useEffect(() => {
    if (navigator.geolocation) {
      navigator.geolocation.getCurrentPosition(
        (position) => {
          setCenter({
            lat: position.coords.latitude,
            lng: position.coords.longitude,
          });
        },
        () => {
          console.log("위치 정보 사용이 거부되었습니다. 기본 위치 사용.");
        }
      );
    }
  }, []);

  // ✅ 모달 열릴 때 지도 생성 및 이벤트 등록
  useEffect(() => {
    if (isOpen && window.kakao) {
      const container = document.getElementById("map");
      const options = {
        center: new window.kakao.maps.LatLng(center.lat, center.lng),
        level: 3,
      };

      const newMap = new window.kakao.maps.Map(container, options);
      setMap(newMap);

      // 📍 지도 이동 시 중심 좌표 업데이트
      kakao.maps.event.addListener(newMap, "center_changed", () => {
        const newCenter = newMap.getCenter();
        setCenter({ lat: newCenter.getLat(), lng: newCenter.getLng() });
      });
    }
  }, [isOpen, center.lat, center.lng]);

  // ✅ 장소 검색 (중심 좌표 기준, 반경 3km)
  const searchPlaces = () => {
    if (!searchKeyword.trim()) {
      alert("검색어를 입력하세요!");
      return;
    }

    if (!map) return;

    const ps = new window.kakao.maps.services.Places();
    const centerCoords = new window.kakao.maps.LatLng(center.lat, center.lng);

    ps.keywordSearch(searchKeyword, (data, status) => {
      if (status === window.kakao.maps.services.Status.OK) {
        const filteredPlaces = data.filter((place) => {
          const distance = getDistance(
            center.lat,
            center.lng,
            place.y,
            place.x
          );
          return distance <= 10000; // 🔍 반경 3km 이내만 표시
        });

        if (filteredPlaces.length === 0) {
          alert("검색 범위 내 결과가 없습니다.");
          setPlaces([]);
          return;
        }

        setPlaces(filteredPlaces);
        displayPlaces(filteredPlaces);
      } else {
        alert("검색 결과가 없습니다.");
        setPlaces([]);
      }
    });
  };

  // ✅ 지도에 마커 표시
  const displayPlaces = (places) => {
    if (!map) return;

    // 기존 마커 제거
    markers.forEach((marker) => marker.setMap(null));
    const newMarkers = [];
    const bounds = new window.kakao.maps.LatLngBounds();

    places.forEach((place) => {
      const position = new window.kakao.maps.LatLng(place.y, place.x);
      const marker = new window.kakao.maps.Marker({ position });

      kakao.maps.event.addListener(marker, "click", () => {
        onSelectPlace({
          place_name: place.place_name,
          address_name: place.road_address_name || place.address_name,
        });
        onClose();
      });

      marker.setMap(map);
      newMarkers.push(marker);
      bounds.extend(position);
    });

    setMarkers(newMarkers);
    map.setBounds(bounds);
  };

  // ✅ 두 좌표 간 거리 계산 함수 (Haversine 공식)
  const getDistance = (lat1, lng1, lat2, lng2) => {
    const toRad = (deg) => (deg * Math.PI) / 180;
    const R = 6371e3; // 지구 반지름 (m)
    const dLat = toRad(lat2 - lat1);
    const dLng = toRad(lng2 - lng1);
    const a =
      Math.sin(dLat / 2) ** 2 +
      Math.cos(toRad(lat1)) * Math.cos(toRad(lat2)) * Math.sin(dLng / 2) ** 2;
    return R * 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
  };

  return isOpen ? (
    <div className="modal">
      <div className="modal-content">
        <input
          type="text"
          placeholder="장소 검색"
          value={searchKeyword}
          onChange={(e) => setSearchKeyword(e.target.value)}
        />
        <button onClick={searchPlaces}>검색</button>
        <div
          id="map"
          style={{ width: "100%", height: "300px", marginTop: "10px" }}
        ></div>
        <ul>
          {places.map((place) => (
            <li
              key={place.id}
              onClick={() => {
                onSelectPlace({
                  place_name: place.place_name,
                  address_name: place.road_address_name || place.address_name,
                });
                onClose();
              }}
              style={{ cursor: "pointer", margin: "5px 0" }}
            >
              {place.place_name} (
              {place.road_address_name || place.address_name})
            </li>
          ))}
        </ul>
        <button onClick={onClose} style={{ marginTop: "10px" }}>
          닫기
        </button>
      </div>
    </div>
  ) : null;
};

export default KakaoMap;
