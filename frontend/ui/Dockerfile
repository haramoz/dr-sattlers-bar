FROM node:14-alpine
# set working direction
WORKDIR '/app'

# install application dependencies
COPY package*.json ./

# add app
COPY . .

RUN npm i

# Build the React application
RUN npm run build

# Set the environment variable
ENV NODE_ENV=production

# Expose port 3001
EXPOSE 3001

# start app
CMD ["npm", "start"]