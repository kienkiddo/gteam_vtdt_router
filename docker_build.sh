#!/bin/bash

set -e

# Bước 1: Build Docker image từ Dockerfile.native
docker build -f graalvm-build.Dockerfile -t my-linux-native .

# Bước 2: Xóa container cũ nếu tồn tại
docker rm -f extract 2>/dev/null || true

# Bước 3: Tạo container tạm
docker create --name extract my-linux-native

# Bước 4: Tạo thư mục dist nếu chưa có
mkdir -p dist

# Bước 5: Copy file thực thi từ container ra host
docker cp extract:/dist/rpg_2d_router_server ./dist/rpg_2d_router_server

# Bước 6: Xoá container tạm
docker rm extract

# Bước 7: Cấp quyền thực thi (Linux/WSL)
chmod +x ./dist/rpg_2d_router_server

echo "✅ Build và extract thành công! File nằm ở ./dist/rpg_2d_router_server"
