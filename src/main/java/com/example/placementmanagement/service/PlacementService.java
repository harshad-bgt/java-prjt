package com.example.placementmanagement.service;

import com.example.placementmanagement.dto.request.PlacementRequestDto;
import com.example.placementmanagement.dto.response.PlacementResponseDto;

import java.util.List;

public interface PlacementService {
    PlacementResponseDto createPlacement(PlacementRequestDto dto);
    PlacementResponseDto getPlacementById(Long id);
    PlacementResponseDto getPlacementByStudentId(Long studentId);
    List<PlacementResponseDto> getAllPlacements();
}
