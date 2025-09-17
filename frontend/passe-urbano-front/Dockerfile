# Build do Angular
FROM node:10.24.1 as builder
WORKDIR /app

COPY package*.json ./
RUN npm install

COPY . .
RUN npm run build --prod

# Servir com Nginx
FROM nginx:alpine
COPY --from=builder /app/dist /usr/share/nginx/html

# Configuração básica do Nginx para SPA
COPY nginx.conf /etc/nginx/conf.d/default.conf

EXPOSE 4200
CMD ["nginx", "-g", "daemon off;"]
