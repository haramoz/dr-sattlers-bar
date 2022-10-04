# pull the official base image
FROM node:16-alpine
# set working direction
WORKDIR '/app'

# install application dependencies
COPY package.json .

RUN npm i
# add app
COPY . ./
# start app
CMD ["npm", "start"]