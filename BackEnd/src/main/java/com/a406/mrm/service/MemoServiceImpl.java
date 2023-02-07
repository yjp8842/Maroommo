package com.a406.mrm.service;

import com.a406.mrm.model.dto.MemoDto;
import com.a406.mrm.model.entity.Memo;
import com.a406.mrm.repository.MemoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MemoServiceImpl implements MemoService{

    private final MemoRepository memoRepository;

    @Override
    public void insertMemo(MemoDto memoDto) throws Exception {

        Memo memo = null;

        // room의 메모일 경우
        if(memoDto.getUserId().equals("")){
            memo = memoRepository.findByRoomId(memoDto.getRoomId());
        }
        // 회원의 메모일 경우
        else{
            memo = memoRepository.findByUserId(memoDto.getUserId());
        }

        if(memoDto == null){
            memoRepository.save(new Memo(memoDto));
        }
        else {
            memo.setContent(memoDto.getContent());
            memoRepository.save(memo);
        }
    }

    @Override
    public MemoDto findMemoByUserId(String userId) throws Exception {
        return new MemoDto(memoRepository.findByUserId(userId));
    }

    @Override
    public MemoDto findMemoByRoomId(int roomId) throws Exception {
        return new MemoDto(memoRepository.findByRoomId(roomId));
    }

    @Override
    public List<MemoDto> findAll() throws Exception {
        return memoRepository.findAll()
                            .stream()
                            .map(x->new MemoDto(x))
                            .collect(Collectors.toList());
    }
}
