package com.sparta.campproject.service;

import com.sparta.campproject.dto.CampDto;
import com.sparta.campproject.dto.CampRequestDto;
import com.sparta.campproject.entity.Camp;
import com.sparta.campproject.entity.Member;
import com.sparta.campproject.mapper.CampMapper;
import com.sparta.campproject.repository.CampRepository;
import com.sparta.campproject.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor //final로 선언한 변수가 있으면 꼭 생성해달라는 것
@Service
public class CampService {

    private final MemberRepository memberRepository;
    private final CampRepository campRepository;

    public String getNickname() {
        String userId = SecurityContextHolder.getContext().getAuthentication().getName();
        Optional<Member> member = memberRepository.findById(Long.valueOf(userId));
        return member.get().getNickname();
    }

    public Camp registerCamp(CampRequestDto requestDto) {
        String nickname = getNickname();
        Camp camp = Camp.builder()
                .nickname(nickname)
                .title(requestDto.getTitle())
                .location(requestDto.getLocation())
                .review(requestDto.getReview())
                .build();

        campRepository.save(camp);

        return camp;
    }

    public List<CampDto> getCamps() {
        List<Camp> campList = campRepository.findAll();
        List<CampDto> campDtos = new ArrayList<>();
        for (Camp camp : campList) {
            CampDto campDto = CampMapper.INSTANCE.campToDto(camp);
            campDtos.add(campDto);
        }
        return campDtos;
    }

    public Camp showCampDetail(Long campid) {
        return campRepository.findById(campid)
                .orElseThrow(() -> new IllegalArgumentException("해당 추천글이 존재하지 않습니다."));
    }

    @Transactional
    public Camp update(Long id, CampRequestDto requestDto) {
        Camp camp = campRepository.findById(id).orElseThrow( //[3번]  수정할 id에 해당하는 데이터를 repo에서 찾고 해당id를 갖는 memo를 호출한다.
                () -> new IllegalArgumentException("추천글이 존재하지 않습니다")
        );
        if (!getNickname().equals(camp.getNickname())) {
            throw new IllegalArgumentException("작성자만 수정할 수 있습니다.");
        }
        camp.update(requestDto);
        return camp;
    }

    public boolean delete(Long campid) {
        Camp camp = campRepository.findById(campid).orElseThrow(
                () -> new IllegalArgumentException("메모가 존재하지 않습니다")
        );
        if (!getNickname().equals(camp.getNickname())) {
            throw new IllegalArgumentException("작성자만 삭제할 수 있습니다.");
        }
//            DeletedUrlPath deletedUrlPath = new DeletedUrlPath();
//            deletedUrlPath.setDeletedUrlPath(camp.getUrlPath());
//            deletedUrlPathRepository.save(deletedUrlPath);

        campRepository.deleteById(campid);
        return true;
    }
}
//    public List<Camp> getCamps() {
//        List<Camp> campList = campRepository.findAll();
//
//        }

//        List<Camp> memos = campRepository.findAll();
//        List<MemoMainResponseDto> responseDto = new ArrayList<>();
//        for (Camp camp : memos) {
//            responseDto.add(new MemoMainResponseDto(camp));
//        }
//        return responseDto;
